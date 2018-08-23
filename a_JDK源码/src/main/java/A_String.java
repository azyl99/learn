
/**
 * https://blog.csdn.net/mazhimazh/article/details/17708001<br/>
 * （1）编码字符集<br/>
 *    编码字符集是一个字符集，它为每一个字符分配一个唯一数字。Unicode 标准的核心是一个编码字符集，
 *    字母“A”的编码为0041和字符“€”的编码为20AC。Unicode标准始终使用十六进制数字，
 *    而且在书写时在前面加上前缀“U+”，所以“A”的编码书写为“U+0041”。<br/>
 *
 * （2）代码点code point和代码单元<br/>
 *    代码点是指可用于编码字符集的数字。编码字符集定义一个有效的代码点范围，
 *    但是并不一定将字符分配给所有这些代码点。<b>有效的 Unicode代码点范围是 U+0000 至 U+10FFFF</b>
 *    代码单元可以理解为字符编码的一个基本单元，最常用的代码单元是字节（即8位），但是16位和32位整数也可以用于内部处理。<br/>
 *
 * （3）增补字符<br/>
 *    16 位编码的所有 65，536 个字符并不能完全表示全世界所有正在使用或曾经使用的字符。
 *    于是，Unicode 标准已扩展到包含多达 1，112，064 个字符。那些超出原来的16 位限制的字符被称作增补字符。
 *
 *    <b>Java的char类型是固定16bits的</b>。代码点在U+0000 — U+FFFF之内到是可以用一个char完整的表示出一个字符。
 *    但代码点在U+FFFF之外的，一个char无论如何无法表示一个完整字符。
 *    这样用char类型来获取字符串中的那些代码点在U+FFFF之外的字符就会出现问题。<br/>
 *
 *    因此，Java 平台不仅需要支持增补字符，而且必须使应用程序能够方便地做到这一点。
 *    Java Community Process 召集了一个专家组，以期找到一个适当的解决方案。
 *    该小组被称为JSR-204专家组，使用Unicode 增补字符支持的Java技术规范请求的编号。<br/>
 *
 *    <b>增补字符是代码点在 U+10000 至 U+10FFFF 范围之间的字符</b>，也就是那些使用原始的 Unicode 的 16 位设计无法表示的字符。
 *    <b>从U+0000 至 U+FFFF 之间的字符集有时候被称为基本多语言面 （BMP UBasic Multilingual Plane ）。</b>
 *    因此，每一个 Unicode 字符要么属于 BMP，要么属于增补字符。<br/>
 * <br/>
 * 二、基于Unicode的具体编码格式<br/>
 * UTF-32 即将每一个 Unicode 代码点表示为相同值的32位整数。
 * 很明显，它是内部处理最方便的表达方式，但是，如果作为一般字符串表达方式，则要消耗更多的内存。<br/>
 *
 * UTF-16 使用一个或两个未分配的16位代码单元的序列对 Unicode 代码点进行编码。
 * 假设U是一个代码点，也就是Unicode编码表中一个字符所对应的Unicode值。<br/>
 * <br/>
 *    (1) 如果在BMP级别中，那么16bits(一个代码单元)就足够表示出字符的Unicode值。<br/>
 *    (2) 如果U+10FFFF > U >= U+10000，也就是处于增补字符级别中。
 *    UTF-16用2个16位来表示出了，并且正好将每个16位都控制在替代区域U+D800 ~ U+DFFF
 *    （其中<b> \uD800 - \uDBFF</b>为高代理项 范围，<b> \uDC00 - \uDFFF </b>为低代理项 范围） 中。<br/>
 * <br/>
 * 下面来看一下Java是如何处理这些增补字符的。  <br/>
 *
 *    分别初始化2个16位无符号的整数 —— W1和W2。
 *    其中W1=110110yyyyyyyyyy（0xD800-0xDBFF）,W2 = 110111xxxxxxxxxx(0xDC00-OxDFFF)。
 *    然后，将Unicode的高10位分配给W1的低10位，将Unicode 的低10位分配给W2的低10位。
 *    这样就可以将20bits的代码点U拆成两个16bits的代码单元。而且这两个代码点正好落在替代区域U+D800-U+DFFF中。<br/>
 *<br/>
 * 举个例子：代码点U+1D56B（使用4个字节表示的代码点）<br/>
 *
 * 0x1D56B= 0001 1101 01 - 01 0110 1011<br/>
 *
 * 将0x1D56B的高10位0001 1101 01分配给W1的低10位组合成110110 <b>0001 1101 01</b>=0xD835<br/>
 * 将0x1D56B的低10位01 0110 1011分配给W2的低10位组合成110111 <b>01 0110 1011</b>=0xDD6B<br/>
 * 这样代码点U+1D56B采用UTF-16编码方式，用2个连续的代码单元U+D835和U+DD6B表示出了。<br/>
 *
 * <br/>
 * <b>UTF-8</b> 使用一至四个字节的序列对编码 Unicode 代码点进行编码。
 * U+0000 至 U+007F 使用一个字节编码，U+0080 至 U+07FF 使用两个字节，
 * U+0800 至 U+FFFF 使用三个字节，而 U+10000 至 U+10FFFF 使用四个字节。
 * UTF-8 设计原理为：字节值 0x00 至 0x7F 始终表示代码点 U+0000 至 U+007F（Basic Latin 字符子集，它对应 ASCII 字符集）。
 * 这些字节值永远不会表示其他代码点，这一特性使 UTF-8 可以很方便地在软件中将特殊的含义赋予某些 ASCII 字符。<br/>
 * <br/>
 * 以下是Unicode和UTF-8之间的转换关系表：<br/>
 *
 * U-00000000 - U-0000007F： 0xxxxxxx <br/>
 * U-00000080 - U-000007FF： 110xxxxx 10xxxxxx <br/>
 * U-00000800 - U-0000FFFF： 1110xxxx 10xxxxxx 10xxxxxx <br/>
 * U-00010000 - U-001FFFFF： 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx <br/>
 * 后两者目前超出了有效Unicode代码点的范围
 * U-00200000 - U-03FFFFFF： 111110xx 10xxxxxx 10xxxxxx 10xxxxx x 10xxxxxx <br/>
 * U-04000000 - U-7FFFFFFF： 1111110x 10xxxxxx 10xxxxxx 10xxxxxx 10xxxxxx 10xxxxxx <br/>
 */
public class A_String {
    public static String str = "\uD835\uDD6B汉a";// char value[] = {'\uD835','\uDD6B','汉','a'};

    public static void main(String[] args) {
        System.out.printf("str.length(): %d\n", str.length());
        System.out.printf("str.codePointCount(0, 1): %d\n", str.codePointCount(0, 1));
        System.out.printf("str.codePointCount(0, 2): %d\n", str.codePointCount(0, 2));
        System.out.printf("str.codePointCount(0, 3): %d\n", str.codePointCount(0, 3));
        System.out.printf("str.codePointCount(0, str.length()): %d\n", str.codePointCount(0, str.length()));
        System.out.printf("str: %s\n", str);
        System.out.printf("str.charAt(0): %c\n", str.charAt(0));
        System.out.printf("str.charAt(1): %c\n", str.charAt(1));
        System.out.printf("str.charAt(2): %c\n", str.charAt(2));
        System.out.printf("str.charAt(3): %c\n", str.charAt(3));
        System.out.printf("str.codePointAt(0): 0x%x\n", str.codePointAt(0));    // value[0], value[1]
        System.out.printf("str.codePointAt(1): 0x%x\n", str.codePointAt(1));    // value[1]
        System.out.printf("str.codePointAt(2): 0x%x\n", str.codePointAt(2));
        System.out.printf("str.codePointAt(3): 0x%x\n", str.codePointAt(3));
        System.out.printf("str.codePointBefore(1): 0x%x\n", str.codePointBefore(1));// value[0]
        System.out.printf("str.codePointBefore(2): 0x%x\n", str.codePointBefore(2));// value[0], value[1]
        System.out.printf("str.codePointBefore(3): 0x%x\n", str.codePointBefore(3));
        System.out.printf("str.codePointBefore(0): 0x%x\n", str.codePointBefore(4));

    }

}
