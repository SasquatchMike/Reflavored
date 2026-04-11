package com.leclowndu93150.reflavored.entity;

import com.leclowndu93150.reflavored.init.ModEffects;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;


public class SkunkEntity extends PathfinderMob implements GeoEntity {

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    private static final RawAnimation WALK = RawAnimation.begin().thenLoop("walk");
    private static final RawAnimation SPRAY = RawAnimation.begin().thenPlay("spray");

    public SkunkEntity(EntityType<? extends SkunkEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SkunkPanicGoal(this));
        this.goalSelector.addGoal(2, new SkunkAvoidPlayerGoal(this, 16.0F));
        this.goalSelector.addGoal(3, new SkunkAvoidUndeadGoal(this, 16.0F));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.4F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<GeoEntity>(this, "Walking", animationState -> animationState.isMoving() ? animationState.setAndContinue(WALK) : PlayState.STOP));
        controllers.add(new AnimationController<GeoAnimatable>(this, "Spray", animationState -> PlayState.STOP).triggerableAnim("spray", SPRAY));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }

    static class SkunkPanicGoal extends PanicGoal {

        public SkunkPanicGoal(PathfinderMob mob) {
            super(mob, 1.4F);
        }

        @Override
        public void start() {
            super.start();
            DamageSource damageSource = this.mob.getLastDamageSource();
            if (damageSource != null && damageSource.getEntity() instanceof LivingEntity livingEntity && !livingEntity.hasEffect(ModEffects.STINK_EFFECT) && !this.mob.level().isClientSide) {
                ((SkunkEntity) this.mob).triggerAnim("Spray", "spray");
                livingEntity.addEffect(new MobEffectInstance(ModEffects.STINK_EFFECT, 600));
            }
        }
    }

    static class SkunkAvoidPlayerGoal extends AvoidEntityGoal<Player> {

        public SkunkAvoidPlayerGoal(PathfinderMob mob, float maxDistance) {
            super(mob, Player.class, maxDistance, 1.6F, 1.4F);
        }

        @Override
        public boolean canUse() {
            this.toAvoid = this.mob.level().getNearestPlayer(this.mob.getX(), this.mob.getY(), this.mob.getZ(), this.maxDist, player -> player.isSprinting() && !player.isSpectator());
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

        @Override
        public void start() {
            super.start();
            if (this.toAvoid != null && !this.toAvoid.hasEffect(ModEffects.STINK_EFFECT) && !this.mob.level().isClientSide) {
                ((SkunkEntity) this.mob).triggerAnim("Spray", "spray");
                this.toAvoid.addEffect(new MobEffectInstance(ModEffects.STINK_EFFECT, 600));
            }
        }
    }

    static class SkunkAvoidUndeadGoal extends AvoidEntityGoal<Monster> {

        public SkunkAvoidUndeadGoal(PathfinderMob mob, float maxDistance) {
            super(mob, Monster.class, maxDistance, 1.6F, 1.4F);
        }

        @Override
        public boolean canUse() {
            this.toAvoid = this.mob.level().getNearestEntity(this.mob.level().getEntitiesOfClass(this.avoidClass, this.mob.getBoundingBox().inflate(this.maxDist, 3.0, this.maxDist), (p_148078_) -> p_148078_.getType().is(EntityTypeTags.UNDEAD)), TargetingConditions.DEFAULT, this.mob, this.mob.getX(), this.mob.getY(), this.mob.getZ());
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

        @Override
        public void start() {
            super.start();
            if (this.toAvoid != null && !this.toAvoid.hasEffect(ModEffects.STINK_EFFECT) && !this.mob.level().isClientSide) {
                ((SkunkEntity) this.mob).triggerAnim("Spray", "spray");
                this.toAvoid.addEffect(new MobEffectInstance(ModEffects.STINK_EFFECT, 600));
            }
        }
    }

}