package edu.baylor.ecs.csi5324.decorator;

import edu.baylor.ecs.csi5324.decorator.impl.CompressionDecorator;
import edu.baylor.ecs.csi5324.decorator.impl.EncryptionDecorator;
import edu.baylor.ecs.csi5324.decorator.impl.FileDataSource;

public class DecoratorDemo {
    public static void main(String[] args) {
        String salaryRecords = "Name,Salary\nJeff Bezos,100000\nElon Musk,912000";
        DataSourceDecorator encoded = new CompressionDecorator(
                                         new EncryptionDecorator(
                                             new FileDataSource("OutputDemo.txt")));
        encoded.writeData(salaryRecords);
        DataSource plain = new FileDataSource("OutputDemo.txt");

        System.out.println("- Input ----------------");
        System.out.println(salaryRecords);
        encoded.writeData(salaryRecords);
        System.out.println("- Encoded --------------");
        System.out.println(plain.readData());
        System.out.println("- Decoded --------------");
        System.out.println(encoded.readData());
    }
}