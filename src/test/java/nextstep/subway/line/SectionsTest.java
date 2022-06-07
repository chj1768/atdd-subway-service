package nextstep.subway.line;

import nextstep.subway.line.domain.Line;
import nextstep.subway.line.domain.Section;
import nextstep.subway.line.domain.Sections;
import nextstep.subway.station.domain.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SectionsTest {
    Station upStation;
    Station downStation;
    Line line;
    Sections sections;

    @BeforeEach
    void setUp() {
        upStation = new Station("상행선");
        downStation = new Station("하행선");
        line = new Line("2호선", "green");
        sections = new Sections();
        sections.add(new Section(line, upStation, downStation, 10));
    }

    @DisplayName("상행종점역을 찾는다")
    @Test
    void findUpStation() {
        Station newStation = new Station("신규역");
        sections.add(new Section(line, newStation, upStation, 5));
        assertThat(sections.findUpStation()).isEqualTo(newStation);
    }

    @DisplayName("구간을 등록하고 노선의 역을 순서대로 찾는다")
    @Test
    void getStations() {
        Station newStation = new Station("신규역");
        sections.addStation(new Section(line, newStation, upStation, 5));
        assertThat(sections.getStations()).containsExactly(newStation, upStation, downStation);
    }

    @DisplayName("신규역을 상행선과 하행선 사이에 추가한다")
    @Test
    void addStation() {
        Station newStation = new Station("신규역");
        sections.addStation(new Section(line, newStation, downStation, 5));
        assertThat(sections.getStations()).containsExactly(upStation, newStation, downStation);
    }

    @DisplayName("구간을 삭제한다")
    @Test
    void removeStation() {
        Station newStation = new Station("신규역");
        sections.addStation(new Section(line, newStation, upStation, 5));
        sections.remove(upStation);
        assertThat(sections.getStations()).containsExactly(newStation, downStation);
    }
}
