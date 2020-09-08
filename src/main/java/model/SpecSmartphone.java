package model;

public class SpecSmartphone {
    private String productId, screen, operaSystem, cameraFont, cameraEnd, cpu, ram, memory, sim, pin;

    public SpecSmartphone(String productId, String screen, String operaSystem, String cameraFont, String cameraEnd, String cpu, String ram, String memory, String sim, String pin) {
        this.productId = productId;
        this.screen = screen;
        this.operaSystem = operaSystem;
        this.cameraFont = cameraFont;
        this.cameraEnd = cameraEnd;
        this.cpu = cpu;
        this.ram = ram;
        this.memory = memory;
        this.sim = sim;
        this.pin = pin;
    }

    public SpecSmartphone() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getOperaSystem() {
        return operaSystem;
    }

    public void setOperaSystem(String operaSystem) {
        this.operaSystem = operaSystem;
    }

    public String getCameraFont() {
        return cameraFont;
    }

    public void setCameraFont(String cameraFont) {
        this.cameraFont = cameraFont;
    }

    public String getCameraEnd() {
        return cameraEnd;
    }

    public void setCameraEnd(String cameraEnd) {
        this.cameraEnd = cameraEnd;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getSim() {
        return sim;
    }

    public void setSim(String sim) {
        this.sim = sim;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}