package hello.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class TrafficController {

    @GetMapping("cpu")
    public String cpu() { //일부러 부하를 주기 위해 만든 컨트롤러
        log.info("cpu");
        long value = 0;
        for(int i = 0 ; i < 100000000000L ; i++) {
            value++;
        }

        return "value = " + value;
    }



    private List<String> list = new ArrayList<>();
    @GetMapping("jvm")
    public String jvm() {
        log.info("jvm");

        for(int i = 0 ; i < 1000000 ; i++) {
            list.add("hello jvm!" + i);
        }

        return "jvm ok";
    }



    @Autowired
    DataSource dataSource;

    @GetMapping("jdbc")
    public String jdbc() throws SQLException {
        log.info("jdbc");
        Connection conn = dataSource.getConnection();
        log.info("connection info = {}", conn);
        //conn.close(); //원래 실행시켜 커넥션 풀을 닫아야 하지만, 커넥션 풀 부하를 위해 닫지 않았다.
        return "connection";
    }

    @GetMapping("error-log")
    public String errorLog() {
        log.error("error log");
        return "error";
    }


}
