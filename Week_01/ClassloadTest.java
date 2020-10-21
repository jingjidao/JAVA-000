

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ClassloadTest extends ClassLoader{
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        ClassloadTest test = new ClassloadTest();
        Class clazz = test.loadClass("Hello");
        Method method = clazz.getMethod("hello");
        method.invoke(clazz.newInstance(),null);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        FileInputStream  inputStream = null;
        byte[] bytes = null;
        try {
            inputStream = new FileInputStream(new File("C:\\IdeaProjects\\JAVA-000\\Week_01\\Hello.xlass"));
            FileChannel channel = inputStream.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(399);
            channel.read(buffer);
            bytes = buffer.array();
            for (int i = 0; i < 399; i++) {
                bytes[i] = (byte)(255 - bytes[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return defineClass(name, bytes, 0, 399);
    }
}
