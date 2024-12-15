public record Song(String name, String path) {
    public String getName() {
        return name;
    }
    public Song(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}