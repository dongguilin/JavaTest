package com.guilin.java.lombok;

import com.guilin.java.lombok.model.User;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

/**
 * Created by guilin on 2017/4/24.
 * <p>
 * lombok 提供了简单的注解的形式来帮助我们简化消除一些必须有但显得很臃肿的 java 代码
 * lombok本质上就是这样的一个实现了"JSR 269 API"的程序，能够在编译时得到调用，修改AST语法树
 *
 * @Data ：注解在类上；提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、toString 方法
 * @Setter：注解在属性上；为属性提供 setting 方法
 * @Getter：注解在属性上；为属性提供 getting 方法
 * @Log4j ：注解在类上；为类提供一个 属性名为log 的 log4j 日志对象
 * @NoArgsConstructor：注解在类上；为类提供一个无参的构造方法
 * @AllArgsConstructor：注解在类上；为类提供一个全参的构造方法
 */
public class TestUser {

    @Test
    public void test1() {
        User user = new User();
        user.setName("张三");
        user.setAddress("河南");
        user.setAge(12);
        user.setMale(true);
        System.out.println(user);
        User user2 = new User("lili", 22, "shanghai", false);
        System.out.println(user2);
        assertThat(user.equals(user2), is(false));

        User user3 = new User(user.getName(), user.getAge(), user.getAddress(), user.isMale());
        assertThat(user.equals(user3), is(true));
    }
}
