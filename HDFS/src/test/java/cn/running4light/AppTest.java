package cn.running4light;

import static org.junit.Assert.assertTrue;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }
    @Test
    public void getFileSystem1() throws IOException, URISyntaxException {
        // 方式1
        Configuration configuration = new Configuration();
        // 指定文件系统类型
        configuration.set("fs.defaultFS","hdfs://pc-1:8020/");
        // 获取指定的文件系统
        FileSystem fileSystem = FileSystem.get(configuration);
        System.err.println(fileSystem);


        // 方式2
        FileSystem fileSystem1 = FileSystem.get(new URI("hdfs://pc-1:8020/"),new Configuration());
        System.err.println(fileSystem1);

        // 方式3
        FileSystem fileSystem2 = FileSystem.newInstance(configuration);
        System.err.println(fileSystem2);

        // 方式4
        FileSystem fileSystem3 = FileSystem.newInstance(new URI("hdfs://pc-1:8020/"),new Configuration());
        System.err.println(fileSystem3);

        FileSystem fileSystem4 = FileSystem.get(new URI("hdfs://pc-1:8020/"),new Configuration());
        System.err.println(fileSystem4);
        fileSystem1.close();
        fileSystem2.close();
        fileSystem3.close();
        fileSystem4.close();
    }
    @Test
    public void apis() throws URISyntaxException, IOException {
        FileSystem fileSystem1 = FileSystem.get(new URI("hdfs://pc-1:8020/"),new Configuration());
        // 遍历文件
        RemoteIterator<LocatedFileStatus> locatedFileStatusRemoteIterator = fileSystem1.listFiles(new Path("/"), true);
        while(locatedFileStatusRemoteIterator.hasNext()){
            System.err.println(locatedFileStatusRemoteIterator.next().getPath());
        }
        fileSystem1.close();
    }
}
