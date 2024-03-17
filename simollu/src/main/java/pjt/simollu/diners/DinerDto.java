package pjt.simollu.diners;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder @NoArgsConstructor @AllArgsConstructor @Data
public class DinerDto {
    private String name; // 상호명
    private String description; // 설명
    private String businessNumber; // 사업자 번호
    private List<DinerMenu> menus; // 메뉴 정보
    private String openingHours; // 영업 시간
}
