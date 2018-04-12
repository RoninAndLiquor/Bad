package high.Test;

import high.main.Application;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

//其中SpringRunner 是SpringJUnit4ClassRunner 的子类 是对SpringJunit4ClassRunner的扩展
//官方文档上说 SPringRunner是SpringJUnit4ClassRunner的别名
//所以这里使用SpringRunner也是可以的
//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
@WebAppConfiguration
public class BaseTest {

}
