package edu.hw10.task1;

import edu.hw10.task1.models.Employee;
import edu.hw10.task1.models.RecordEmployee;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RandomObjectGeneratorTest {
    private final RandomObjectGenerator rog = new RandomObjectGenerator();

    @Test
    void createObjectWithConstructor()
        throws InvocationTargetException,
               InstantiationException,
               IllegalAccessException {
        var myClass = rog.nextObject(Employee.class);

        assertThat(myClass.getAge()).isBetween(18, 70);
        assertThat(myClass.getName()).isNotNull();
    }

    @Test
    void createObjectWithStaticMethod()
        throws InvocationTargetException,
        InstantiationException,
        IllegalAccessException {
        var myClass = rog.nextObject(Employee.class, "create");

        assertThat(myClass.getAge()).isBetween(18, 70);
        assertThat(myClass.getName()).isNotNull();
    }

    @Test
    void createRecord()
        throws InvocationTargetException,
               InstantiationException,
               IllegalAccessException {
        var myRecord = rog.nextObject(RecordEmployee.class);

        assertThat(myRecord.age()).isBetween(18, 70);
        assertThat(myRecord.name()).isNotNull();
    }
}
