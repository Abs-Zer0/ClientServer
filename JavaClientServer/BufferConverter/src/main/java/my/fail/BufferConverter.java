/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.fail;

/**
 *
 * @author abs-zer0
 */
public class BufferConverter {

    public static byte[] IntToBuff(int value) {
        byte[] res = new byte[4];

        for (int i = 0; i < res.length; i++) {
            res[i] = (byte) (value % 256);
            value /= 256;
        }

        return res;
    }

    public static int BuffToInt(byte[] arr) {
        int res = 0;
        
        for (int i = 0; i < 4; i++) {
            res += Math.pow(256, i) * Byte.toUnsignedInt(arr[i]);
        }
        
        return res;
    }
}
