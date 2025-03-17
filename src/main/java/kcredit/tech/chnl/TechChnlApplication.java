package kcredit.tech.chnl;

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
}
