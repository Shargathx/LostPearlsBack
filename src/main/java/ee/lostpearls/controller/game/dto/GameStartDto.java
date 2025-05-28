package ee.lostpearls.controller.game.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameStartDto {

    private String gameStatus;
    private Long gameStartMilliseconds;
}
