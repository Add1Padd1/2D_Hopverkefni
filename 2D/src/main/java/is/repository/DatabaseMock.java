package is.repository;



import is.vinnsla.Tour;
import is.vinnsla.User;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class DatabaseMock implements DatabaseInterface {


    public static void generateTours(ObservableList<Tour> tours) {
        tours.add(new Tour("Fjallaferð", 120, "cover1.jpg", "Akureyri","Þetta er gönguferð um fjöll Akureyrar", LocalDate.now(), LocalTime.of(10, 0),7500, 75));
        tours.add(new Tour("Selfossarferð", 90, "cover2.jpg", "Selfoss","Þetta er gönguferð þar sem við skoðum fossa í Selfossi", LocalDate.now(), LocalTime.of(12, 0),6500,75));
        tours.add(new Tour("Fossaferð", 150, "cover3.jpg", "Gullfoss","Þetta er gönguferð þar sem við skoðum Gullfoss", LocalDate.now(), LocalTime.of(14, 0),9000,75));
        tours.add(new Tour("Reykjavíkurferð", 180, "leopard.jpg", "Reykjavík","Þetta er ferð þar sem við keyrum um Reyjavík", LocalDate.now(), LocalTime.of(16, 0),800,75));
    }


    public List<Tour> generateTours() {
        return null;
    }
}
