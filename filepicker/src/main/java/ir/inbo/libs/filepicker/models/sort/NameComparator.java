package ir.inbo.libs.filepicker.models.sort;

import java.util.Comparator;
import ir.inbo.libs.filepicker.models.Document;


public class NameComparator implements Comparator<Document> {

    protected NameComparator() {
    }

    @Override
    public int compare(Document o1, Document o2) {
        return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
    }
}
