package graph.to.db.entity;

public class PlotData {
    private final Double x;
    private final Double y;
    private final Double z;
    private final Double h;
    private final String location;
    private final String name;
    private final Double t;
    private final Double mu;
    private final Double z_0;
    public PlotData(Double x,Double y,Double z,Double h,Double t, Double mu, Double z_0, String location, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.h = h;
        this.t = t;
        this.mu = mu;
        this.z_0 = z_0;
        this.location = location;
        this.name = name;
    }

    public Double getT() {
        return t;
    }

    public Double getMu() {
        return mu;
    }

    public Double getZ_0() {
        return z_0;
    }

    public Double getY() {
        return y;
    }

    public Double getZ() {
        return z;
    }

    public Double getH() {
        return h;
    }

    public String getName() {
        return name;
    }

    public Double getX() {
        return x;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlotData)) return false;
        PlotData plotData = (PlotData) o;
        return x.equals(plotData.x) && location.equals(plotData.location) && name.equals(plotData.name);
    }

    @Override
    public int hashCode() {
        int result = 12;
        result += result * x.hashCode();
        result += result * name.hashCode();
        result += result * location.hashCode();
        return result;
    }
}
