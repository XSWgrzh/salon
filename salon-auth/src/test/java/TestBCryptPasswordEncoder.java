
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author：xieshaowei
 * @Package：PACKAGE_NAME
 * @Project：salon
 * @name：TestBCryptPasswordEncoder
 * @Date：2023/11/23 10:13
 */
public class TestBCryptPasswordEncoder {

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println(encode);

    }
}
