package pers.guzx.springbootcache;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import pers.guzx.springbootcache.bean.Employee;
import pers.guzx.springbootcache.mapper.DepartmentMapper;
import pers.guzx.springbootcache.mapper.EmployeeMapper;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringBootCacheApplicationTests {

    @Resource
    private DepartmentMapper departmentMapper;

    @Resource
    private EmployeeMapper employeeMapper;

    @Test
    void contextLoads() {
        Employee employee = new Employee();
        employee.setEmail("guzx@qq.com");
        employee.setGender(1);
        employee.setLastName("gu");
        employee.setdId(1);
        employeeMapper.insertEmployee(employee);
    }

//    @Resource
//    private RedisTemplate template;

    @Resource
    private RedisTemplate<Object, Employee> empRedisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testRedis() {
//        stringRedisTemplate.opsForValue().set("age","20");
        stringRedisTemplate.opsForValue().append("name", "顾志雄");
        Employee empById = employeeMapper.getEmpById(1);
        empRedisTemplate.opsForValue().set("emp", empById);
    }

}
