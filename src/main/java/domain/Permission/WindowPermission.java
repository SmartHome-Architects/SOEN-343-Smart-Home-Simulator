package domain.Permission;

    public class WindowPermission extends Permission {
        private boolean hasWindowPermission;

        public WindowPermission(boolean hasWindowPermission) {
            this.hasWindowPermission = hasWindowPermission;
        }

        public boolean isHasWindowPermission() {
            return hasWindowPermission;
        }

        public void setHasWindowPermission(boolean hasWindowPermission) {
            this.hasWindowPermission = hasWindowPermission;
        }

        @Override
        public String toString() {
            return "WindowPermission{" +
                    "hasWindowPermission=" + hasWindowPermission +
                    '}';
        }
}
