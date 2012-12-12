/*
 * User: joel
 * Date: 2012-11-23
 * Time: 20:27
 */
package se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.util;

/**
* TODO describe class!
*/
public class Ref<T> {
    private T ref;

    public Ref(T ref) {
        this.ref = ref;
    }

    public T get() {
        return ref;
    }
}
