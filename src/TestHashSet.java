public class TestHashSet {
    public static void main(String[] args) {
        MyHashSet<String> names = new MyHashSet<>();
        names.add("Vasya");
        names.add("Petya");
        names.add("Sofa");
        System.out.println(names.size());
        System.out.println(names.contains("Vasya"));

        names.add("Vasya");
        System.out.println(names.size());
        names.remove("Sofa");
        System.out.println(names.size());

        names.add("Anna");
        names.add("David");
        names.add("Alex");
        names.add("Andrew");
        names.add("Rodion");
        names.add("Artem");
        names.add("Anna1");
        names.add("David1");
        names.add("Alex1");
        names.add("Andrew1");
        names.add("Rodion1");
        names.add("Artem1");
        names.add("Artem2");
        names.add("David3");
        names.add("Alex3");
        names.add("Andrew3");
        names.add("Rodion3");
        names.add("Artem3");
        names.add("Artem3");

        names.forEach(System.out::println);
        System.out.println(names.size());

    }
}
