// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.asm;

import com.google.common.base.Throwables;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class RemoteCallFactory {
    public static IObjectEvaluate<ItemStack> pulverizer;
    public static IObjectEvaluate nullValuate;
    static LaunchClassLoader cl;
    static Method m_defineClass;

    static {
        RemoteCallFactory.cl = (LaunchClassLoader) RemoteCallFactory.class.getClassLoader();
        try {
            (RemoteCallFactory.m_defineClass = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE)).setAccessible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        RemoteCallFactory.pulverizer = getEvaluator("cofh.thermalexpansion.util.crafting.PulverizerManager", "recipeExists", ItemStack.class);
        RemoteCallFactory.nullValuate = new IObjectEvaluate() {
            @Override
            public boolean evaluate(final Object object) {
                return false;
            }
        };
    }

    public static <T> IObjectEvaluate<T> getEvaluator(final String baseClass, final String baseMethod, final Class param) {
        try {
            final Class<?> clazz = Class.forName(baseClass);
            final Method method = clazz.getDeclaredMethod(baseMethod, param);
            assert Modifier.isStatic(method.getModifiers());
        } catch (Exception e2) {
            return null;
        }
        final String classname = "XU_caller_" + baseClass.replace('.', '_') + "_" + baseMethod + "_" + param.getSimpleName();
        final String superName = Type.getInternalName(Object.class);
        final ClassWriter cw = new ClassWriter(0);
        cw.visit(50, 33, classname, null, superName, new String[]{Type.getInternalName(IObjectEvaluate.class)});
        cw.visitSource(".dynamic", null);
        final MethodVisitor constructor = cw.visitMethod(1, "<init>", Type.getMethodDescriptor(Type.VOID_TYPE), null, null);
        constructor.visitCode();
        constructor.visitVarInsn(25, 0);
        constructor.visitMethodInsn(183, superName, "<init>", Type.getMethodDescriptor(Type.VOID_TYPE), false);
        constructor.visitInsn(177);
        constructor.visitMaxs(1, 1);
        constructor.visitEnd();
        final MethodVisitor getData = cw.visitMethod(1, "evaluate", Type.getMethodDescriptor(Type.BOOLEAN_TYPE, Type.getType(Object.class)), null, null);
        getData.visitCode();
        if (param != null) {
            getData.visitVarInsn(25, 1);
            if (param != Object.class) {
                getData.visitTypeInsn(192, Type.getInternalName(param));
            }
            getData.visitMethodInsn(184, baseClass.replace('.', '/'), baseMethod, Type.getMethodDescriptor(Type.BOOLEAN_TYPE, Type.getType(param)), false);
        } else {
            getData.visitMethodInsn(184, baseClass.replace('.', '/'), baseMethod, Type.getMethodDescriptor(Type.BOOLEAN_TYPE), false);
        }
        getData.visitInsn(172);
        getData.visitMaxs(1, 2);
        getData.visitEnd();
        cw.visitEnd();
        final byte[] b = cw.toByteArray();
        try {
            final Class<? extends IObjectEvaluate> clazz2 = (Class<? extends IObjectEvaluate>) RemoteCallFactory.m_defineClass.invoke(RemoteCallFactory.cl, classname, b, 0, b.length);
            return (IObjectEvaluate<T>) clazz2.newInstance();
        } catch (Throwable e) {
            throw Throwables.propagate(e);
        }
    }

    public interface IObjectEvaluate<T> {
        boolean evaluate(final T p0);
    }
}


