/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.fail.client;

/**
 *
 * @author ���0������ �0��
 */
public class BufferConverter {

    static {
        System.loadLibrary("convlib");
    }

    native public static byte[] IntToBuff(int value);

    native public static int BuffToInt(byte[] arr);
}
