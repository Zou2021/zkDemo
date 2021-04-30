/**
 * @author: 邹祥发
 * @date: 2021/4/29 16:48
 */
public class Person {
    private String name;
    private Double money;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMoney() {
        return money;
    }

    private OnMoneyListener listener;
    //3:监听需要从外部传进来，本质是将处理代码传进来
    public void setListener(OnMoneyListener listener) {
        this.listener = listener;
    }

    //2:在数据修改处调用监听的方法
    public void setMoney(Double money) {
        if (this.listener != null){
            this.listener.OnChange();
        }
        this.money = money;
    }
}
