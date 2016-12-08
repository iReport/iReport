package com.test.myapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of (dummy) items.
     */
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of (dummy) items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    static {
        // Add 3 sample items.
        addItem(new DummyItem("1", "Discarded Can", "Can discarded on the way side"));
        addItem(new DummyItem("2", "Trash", "Trash on the way side"));
        addItem(new DummyItem("3", "Bags", "Bags on the way side"));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public String content;
        public String meaning;

        public DummyItem(String id, String content, String meaning) {
            this.id = id;
            this.content = content;
            this.meaning = meaning;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
