package koschei.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Wood3 {

    private final Rabbit4 rabbit;

    @Autowired
    public Wood3(Rabbit4 rabbit4) {
        this.rabbit = rabbit4;
    }

    @Override
    public String toString() {
        return ", на дереве заяц " + rabbit.toString();
    }
}
