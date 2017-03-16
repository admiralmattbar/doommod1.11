package org.educraft.mymod.utilclasses;

import javax.annotation.Nullable;

import org.educraft.mymod.entities.EntityBullet;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;

public class ModDamageSource 
{
	DamageSource bulletDamage = new DamageSource("BULLET");

	public static DamageSource causeBulletDamage(EntityBullet bullet, @Nullable Entity indirectEntityIn)
	{
		return (new EntityDamageSourceIndirect("bullet", bullet, indirectEntityIn)).setProjectile();
	}
}