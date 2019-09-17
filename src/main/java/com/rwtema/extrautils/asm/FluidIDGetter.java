// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.asm;

import com.google.common.base.Throwables;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraftforge.fluids.FluidStack;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import java.lang.reflect.Method;

public class FluidIDGetter {
    public static final Class<? extends IFluidLegacy> clazz;
    public static final IFluidLegacy fluidLegacy;

    static {
        final LaunchClassLoader cl = (LaunchClassLoader) FluidIDGetter.class.getClassLoader();
        Method m_defineClass;
        try {
            m_defineClass = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE);
            m_defineClass.setAccessible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        final String classname = "XU_fluidstack_compat_code";
        final String superName = Type.getInternalName(Object.class);
        final ClassWriter cw = new ClassWriter(0);
        cw.visit(50, 33, classname, null, superName, new String[]{Type.getInternalName(IFluidLegacy.class)});
        cw.visitSource(".dynamic", null);
        final MethodVisitor constructor = cw.visitMethod(1, "<init>", Type.getMethodDescriptor(Type.VOID_TYPE), null, null);
        constructor.visitCode();
        constructor.visitVarInsn(25, 0);
        constructor.visitMethodInsn(183, superName, "<init>", Type.getMethodDescriptor(Type.VOID_TYPE), false);
        constructor.visitInsn(177);
        constructor.visitMaxs(1, 1);
        constructor.visitEnd();
        final MethodVisitor getData = cw.visitMethod(1, "getID", Type.getMethodDescriptor(Type.INT_TYPE, Type.getType(FluidStack.class)), null, null);
        getData.visitCode();
        getData.visitVarInsn(25, 1);
        try {
            FluidStack.class.getDeclaredMethod("getFluidID", new Class[0]);
            getData.visitMethodInsn(182, "net/minecraftforge/fluids/FluidStack", "getFluidID", "()I", false);
        } catch (NoSuchMethodException e3) {
            getData.visitFieldInsn(180, "net/minecraftforge/fluids/FluidStack", "fluidID", "I");
        }
        getData.visitInsn(172);
        getData.visitMaxs(1, 2);
        getData.visitEnd();
        cw.visitEnd();
        final byte[] b = cw.toByteArray();
        try {
            clazz = (Class) m_defineClass.invoke(cl, classname, b, 0, b.length);
            fluidLegacy = FluidIDGetter.clazz.newInstance();
        } catch (Exception e2) {
            throw Throwables.propagate(e2);
        }
    }

    public interface IFluidLegacy {
        int getID(final FluidStack p0);
    }
}


