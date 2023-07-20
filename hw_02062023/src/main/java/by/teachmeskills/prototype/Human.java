package by.teachmeskills.prototype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Human implements Prototype {
    private String name;
    private int age;
    private String sex;

    public Human(Human obj) {
        this.name = obj.name;
        this.age = obj.age;
        this.sex = obj.sex;
    }

    @Override
    public Prototype clone() {
        return new Human(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return age == human.age && Objects.equals(name, human.name) && Objects.equals(sex, human.sex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, sex);
    }
}
