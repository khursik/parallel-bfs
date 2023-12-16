package array

import java.lang.invoke.MethodHandles
import java.lang.invoke.VarHandle

class AtomicBooleanArray(size: Int) {
    private val array = BooleanArray(size)

    fun compareAndSet(ind: Int, expectedValue: Boolean, newValue: Boolean): Boolean {
        return varHandle.compareAndSet(array, ind, expectedValue, newValue)
    }

    companion object {
        private val varHandle: VarHandle = MethodHandles.arrayElementVarHandle(BooleanArray::class.java)
    }
}