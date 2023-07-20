package by.teachmeskills.prototype;

public class PrototypeTest {
    public static void main(String[] args) {
        Human human1 = new Human("Darya", 21, "female");
        Human human2 = (Human) human1.clone();
        if (human1 != human2) {
            System.out.println("Human1 and Human2 are different objects!");
        }
        if (human1.equals(human2)) {
            System.out.println("And they are identical");
        }
    }
}
