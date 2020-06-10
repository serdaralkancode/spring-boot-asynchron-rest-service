package tr.salkan.code.java.web.async.restservice.dto.enums;

public enum ProcessModulEnum {

    MODUL1(1,"Modul1"),
    MODUL2(2,"Modul2"),
    MODUL3(3,"Modul3");

    private Integer modulId;
    private String name;

    ProcessModulEnum(Integer modulId, String name) {
        this.modulId = modulId;
        this.name = name;
    }

    public static ProcessModulEnum findByModulName(String modul) {

        for (ProcessModulEnum pm : values()) {
            if (pm.getName().equals(modul)) {
                return pm;
            }
        }
        return null;
    }

    public static ProcessModulEnum findByModulId(Integer id) {

        for (ProcessModulEnum pm : values()) {
            if (pm.getModulId().equals(id)) {
                return pm;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public Integer getModulId() {
        return modulId;
    }
}
