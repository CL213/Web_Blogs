package com.cn.hadoop.HDFS;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cn.redis.RedisClientTemplate;

//具体的HDFS操作类
@Repository("HDFSOperate")
public class HDFSOperate {
	
	private static final Logger log = Logger.getLogger(RedisClientTemplate.class);
	
	@Autowired
	private FileSystem HDFSFileSystem;
	
	/**
     * 测试创建文件/input/25.txt并添加内容hello hdfs\n
     * @throws IllegalArgumentException
     * @throws IOException
     */
    public void create() throws IllegalArgumentException, IOException {
        FSDataOutputStream dos = HDFSFileSystem.create(new Path("/input/25.txt"),true);
        dos.write("hello hdfs\n".getBytes());
        //流开了记得要关闭  不然会报错
        dos.close();
        System.out.println("create over");
    }
    
    /**
     * 测试文件/input/5.txt和/input/25.txt是否存在
     * @throws IOException
     */
    public void exists() throws IOException {
        //测试文件是否存在
        Path path = new Path("/input/5.txt");
        System.out.println(HDFSFileSystem.exists(path));
        //false
        path = new Path("/input/25.txt");
        System.out.println(HDFSFileSystem.exists(path));
        //rue
    }   
    
    /**
     * 测试创建目录
     * 注意路径Skyofyuan和路径/Skyofyuan的不同
     * 路径Skyofyuan
     * 因为spring-hadoop.xml中我们配置了user="root"  这样我们在hadoop环境下进行操作都会默认在用户路径/user/root下
     * 路径/Skyofyuan
     * 就是在路径/Skyofyuan下
     * @throws IOException
     */
    public void mkdir() throws IOException{
        Path path = new Path("Skyofyuan");
        boolean isSucc = HDFSFileSystem.mkdirs(path);
        if(isSucc) {
        	System.out.println("创建目录成功！");
        }
    }
    
    /**
     * 测试文件复制
     * @throws IOException
     */
    public void downloadAndUpload() throws IllegalArgumentException, IOException{
        /*
         *从hdfs上复制到本地文件系统
         *一开始使用copyToLocalFile(src,dst) 方法发生错误
         *后来发现要使用copyToLocalFile(boolean delSrc, Path src, Path dst,
         *boolean useRawLocalFileSystem) 方法，多的两个参数表示第一个表示是否删除源文件，最后一个表示当本地文件系统不存在时使用RawLocalFileSystem
         */
    	//复制文件/output/part-r-00000到本地D盘
    	HDFSFileSystem.copyToLocalFile(false,new Path("/output/part-r-00000"), new Path("D:/"),true);
        //从本地文件系统复制到hdfs上
    	//复制本地D盘文件hadoop-mapreduce-examples-2.8.4.jar到input
    	HDFSFileSystem.copyFromLocalFile(new Path("D:/hadoop-mapreduce-examples-2.8.4.jar"), new Path("/input"));
    }
    
    //显示进度的上传
    public void input() throws IOException {
        String src = "D:/hadoop-mapreduce-examples-2.8.4.jar";
        InputStream in = new BufferedInputStream(new FileInputStream(src));
        Path path = new Path("/input/hadoop-mapreduce-examples-2.8.4.jar");
        //使用hadoop的FSDataOutputStream上传文件
        //对于大型文件还可以使用进度条显示进度
        FSDataOutputStream out = HDFSFileSystem.create(path, new Progressable() {

            @Override
            public void progress() {
                System.out.print(". ");
            }

        });
        //hadoop的工具类
        IOUtils.copyBytes(in, out, 4096, true);
        //. . . . . . 301934
        System.out.println(HDFSFileSystem.getFileStatus(path).getLen());

    } 
    
    //删除，新建，追加
    public void deleteAndCreateNewFile() throws IllegalArgumentException, IOException{
        //true表示需要递归删除
    	HDFSFileSystem.delete(new Path("/output"),true);
        Path path = new Path("/input/5.txt");
        //这个方法与create相比仅仅多了一个，文件是否存在的判断如果存在就不会创建了
        HDFSFileSystem.createNewFile(path);
        //文件内容追加
        FSDataOutputStream out = HDFSFileSystem.append(new Path("/input/5.txt"));
        out.write("\nappend some data to a exists file\n".getBytes());  //这里2个\n均为换行
        out.flush();
        out.close();
    }
	
}