/**
 * @author: 邹祥发
 * @date: 2021/4/29 16:54
 */
public class Demo {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("zou");
        person.setListener(() -> System.out.println(person.getName()+"，你的金额发生改变"));
        person.setMoney(1000.0);
        System.out.println(person.getMoney());
    }
}
