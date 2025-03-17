package kcredit.tech.chnl;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TechChnlApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechChnlApplication.class, args);
    }

    /**
     * properties 암호화
     * <a href="https://www.devglan.com/online-tools/jasypt-online-encryption-decryption">암호값 생성방법</a>
     * 암호화: TypeOfEncrypt="Tow Way Encryption"
     * 복호화: ActionType="DecryptPassword"
     */
    @Bean
    StringEncryptor jasyptStringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("kcredit");
        config.setAlgorithm("PBEWITHMD5ANDDES");
        config.setPoolSize("1");
        encryptor.setConfig(config);
        return encryptor;
    }

    /**
     * 쿼리를 조회하면 자동으로 총건수(selectCount)를 실행하여 page.total 값을 입력합니다.
     * 총건수 조회를 원하지 않으시면 반드시 page1.setSearchCount(false) 를 설정하셔야 합니다.
     * Page 객체를 넘길 때만 발생하며 xml 쿼리에도 동일하게 적용됩니다.
     * 따라서 쿼리문에 paging 관련 구문을 넣으면 안됩니다. (자동으로 넣어줍니다)
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.POSTGRE_SQL)); // DB 별1 페이징 방식이 다릅니다.
        return interceptor;
    }
}
