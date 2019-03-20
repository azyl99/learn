package a_反编译专用.a11_enum;

/**
 * @author guya on 2019/3/18
 */
public enum E {
    A(1),
    B(3),
    ;

    private int code;

    E(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}