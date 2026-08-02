package com.leclowndu93150.reflavored.effect.goal;

import com.leclowndu93150.reflavored.init.ModEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.Vec3;

public class AvoidStinkyGoal extends AvoidEntityGoal<LivingEntity> {

    private int checkCooldown;

    public AvoidStinkyGoal(PathfinderMob mob) {
        super(mob, LivingEntity.class, 16.0F, 1.6F, 1.4F);
    }

    @Override
    public boolean canUse() {
        // This goal is attached to every pathfinder mob in the world, and its check
        // scans every LivingEntity in a ~32x6x32 box. Running that every tick on every
        // mob is very expensive, so throttle it: the stink effect lasts minutes, so a
        // sub-second reaction delay is imperceptible. The per-mob offset also staggers
        // the scans across ticks instead of bunching them on one tick.
        if (this.checkCooldown > 0) {
            this.checkCooldown--;
            return false;
        }
        this.checkCooldown = 20 + this.mob.getRandom().nextInt(20);

        this.toAvoid = this.mob.level().getNearestEntity(this.mob.level().getEntitiesOfClass(LivingEntity.class, this.mob.getBoundingBox().inflate(this.maxDist, 3.0, this.maxDist), entity -> entity != this.mob && entity.hasEffect(ModEffects.STINK_EFFECT)), TargetingConditions.DEFAULT, this.mob, this.mob.getX(), this.mob.getY(), this.mob.getZ());
        if (this.toAvoid == null) {
            return false;
        } else {
            Vec3 vec3 = DefaultRandomPos.getPosAway(this.mob, 16, 7, this.toAvoid.position());
            if (vec3 == null) {
                return false;
            } else if (this.toAvoid.distanceToSqr(vec3.x, vec3.y, vec3.z) < this.toAvoid.distanceToSqr(this.mob)) {
                return false;
            } else {
                this.path = this.pathNav.createPath(vec3.x, vec3.y, vec3.z, 0);
                return this.path != null;
            }
        }
    }
}
