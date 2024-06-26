package domain.Permission;

public class Permission {
    private boolean hasWindowPermissionOutside;
    private boolean hasWindowPermissionInsideHome;
    private boolean hasDoorPermissionOutside;
    private boolean hasDoorPermissionInsideHome;
    private boolean hasLightPermissionOutside;
    private boolean hasLightPermissionInsideHome;
    private boolean hasHeaterPermissionOutside;
    private boolean hasHeaterPermissionInsideHome;
    private boolean hasSHPPermissionOutside;
    private boolean hasSHPPermissionInsideHome;


    public Permission(boolean hasWindowPermissionOutside, boolean hasWindowPermissionInsideHome, boolean hasDoorPermissionOutside, boolean hasDoorPermissionInsideHome,
                      boolean hasLightPermissionOutside, boolean hasLightPermissionInsideHome, boolean hasHeaterPermissionOutside, boolean hasHeaterPermissionInsideHome,
                      boolean hasSHPPermissionOutside, boolean hasSHPPermissionInsideHome) {
        this.hasWindowPermissionOutside = hasWindowPermissionOutside;
        this.hasWindowPermissionInsideHome = hasWindowPermissionInsideHome;
        this.hasDoorPermissionOutside = hasDoorPermissionOutside;
        this.hasDoorPermissionInsideHome = hasDoorPermissionInsideHome;
        this.hasLightPermissionOutside = hasLightPermissionOutside;
        this.hasLightPermissionInsideHome = hasLightPermissionInsideHome;
        this.hasHeaterPermissionOutside = hasHeaterPermissionOutside;
        this.hasHeaterPermissionInsideHome = hasHeaterPermissionInsideHome;
        this.hasSHPPermissionOutside = hasSHPPermissionOutside;
        this.hasSHPPermissionInsideHome = hasSHPPermissionInsideHome;
    }

    public boolean isHasHeaterPermissionOutside() {
        return hasHeaterPermissionOutside;
    }

    public void setHasHeaterPermissionOutside(boolean hasHeaterPermissionOutside) {
        this.hasHeaterPermissionOutside = hasHeaterPermissionOutside;
    }

    public boolean isHasHeaterPermissionInsideHome() {
        return hasHeaterPermissionInsideHome;
    }

    public void setHasHeaterPermissionInsideHome(boolean hasHeaterPermissionInsideHome) {
        this.hasHeaterPermissionInsideHome = hasHeaterPermissionInsideHome;
    }

    public boolean isHasWindowPermissionOutside() {
        return hasWindowPermissionOutside;
    }

    public void setHasWindowPermissionOutside(boolean hasWindowPermissionOutside) {
        this.hasWindowPermissionOutside = hasWindowPermissionOutside;
    }

    public boolean isHasWindowPermissionInsideHome() {
        return hasWindowPermissionInsideHome;
    }

    public void setHasWindowPermissionInsideHome(boolean hasWindowPermissionInsideHome) {
        this.hasWindowPermissionInsideHome = hasWindowPermissionInsideHome;
    }

    public boolean isHasDoorPermissionOutside() {
        return hasDoorPermissionOutside;
    }

    public void setHasDoorPermissionOutside(boolean hasDoorPermissionOutside) {
        this.hasDoorPermissionOutside = hasDoorPermissionOutside;
    }

    public boolean isHasDoorPermissionInsideHome() {
        return hasDoorPermissionInsideHome;
    }

    public void setHasDoorPermissionInsideHome(boolean hasDoorPermissionInsideHome) {
        this.hasDoorPermissionInsideHome = hasDoorPermissionInsideHome;
    }

    public boolean isHasLightPermissionOutside() {
        return hasLightPermissionOutside;
    }

    public void setHasLightPermissionOutside(boolean hasLightPermissionOutside) {
        this.hasLightPermissionOutside = hasLightPermissionOutside;
    }

    public boolean isHasLightPermissionInsideHome() {
        return hasLightPermissionInsideHome;
    }

    public void setHasLightPermissionInsideHome(boolean hasLightPermissionInsideHome) {
        this.hasLightPermissionInsideHome = hasLightPermissionInsideHome;
    }

    public boolean isHasSHPPermissionOutside() {
        return hasSHPPermissionOutside;
    }

    public void setHasSHPPermissionOutside(boolean hasSHPPermissionOutside) {
        this.hasSHPPermissionOutside = hasSHPPermissionOutside;
    }

    public boolean isHasSHPPermissionInsideHome() {
        return hasSHPPermissionInsideHome;
    }

    public void setHasSHPPermissionInsideHome(boolean hasSHPPermissionInsideHome) {
        this.hasSHPPermissionInsideHome = hasSHPPermissionInsideHome;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "hasWindowPermissionOutside=" + hasWindowPermissionOutside +
                ", hasWindowPermissionInsideHome=" + hasWindowPermissionInsideHome +
                ", hasDoorPermissionOutside=" + hasDoorPermissionOutside +
                ", hasDoorPermissionInsideHome=" + hasDoorPermissionInsideHome +
                ", hasLightPermissionOutside=" + hasLightPermissionOutside +
                ", hasLightPermissionInsideHome=" + hasLightPermissionInsideHome +
                ", hasHeaterPermissionOutside=" + hasHeaterPermissionOutside +
                ", hasHeaterPermissionInsideHome=" + hasHeaterPermissionInsideHome +
                ", hasSHPPermissionOutside=" + hasSHPPermissionOutside +
                ", hasSHPPermissionInsideHome=" + hasSHPPermissionInsideHome +
                '}';
    }
}
