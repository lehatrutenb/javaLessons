package hse.kpo.export;

import hse.kpo.domains.HandEngine;
import hse.kpo.domains.LevitationEngine;
import hse.kpo.domains.PedalEngine;
import hse.kpo.interfaces.Engine;
import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement(name="Engine")
public class EngineTypeS {
    @XmlElement(name="Type")
    @Getter
    private String type;

    public Engine getEngine() {
        if (type.equals("HandEngine()")) {
            return new HandEngine();
        }
        if (type.equals("LevitationEngine()")) {
            return new LevitationEngine();
        }
        System.out.println(type);
        int size = Integer.parseInt(type.substring("PedalEngine(size=".length(), type.length() - 1));
        return new PedalEngine(size);
    }
}
