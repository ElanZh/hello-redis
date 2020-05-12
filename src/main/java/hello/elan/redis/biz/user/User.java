package hello.elan.redis.biz.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hello.elan.redis.config.DateTimeJsonComponent;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@lombok.Data
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Builder
public class User {

    @Id
    @Column(length = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String name;

    @Column(length = 10, nullable = false)
    private String gender;

    @JsonSerialize(using = DateTimeJsonComponent.DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeJsonComponent.DateTimeDeserializer.class)
    @Column(nullable = false, updatable = false)
    private LocalDateTime createAt;
}
