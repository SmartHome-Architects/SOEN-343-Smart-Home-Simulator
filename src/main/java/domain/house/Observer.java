package domain.house;

public interface Observer {
    void update(double tempRate, boolean isActive,double outsideTemp);
}