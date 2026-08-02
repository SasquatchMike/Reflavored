package com.leclowndu93150.reflavored.entity;

import com.leclowndu93150.reflavored.init.ModEffects;
import com.leclowndu93150.reflavored.init.ModEntities;
import com.leclowndu93150.reflavored.init.ModPotions;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Predicate;


public class SkunkEntity extends Animal implements GeoEntity {

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    private static final RawAnimation WALK = RawAnimation.begin().thenLoop("walk");
    private static final RawAnimation SPRAY = RawAnimation.begin().thenPlay("spray");

    public SkunkEntity(EntityType<? extends SkunkEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SkunkSprayGoal(this));
        this.goalSelector.addGoal(2, new PanicGoal(this, 1.4F));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.1D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, stack -> stack.is(Items.SWEET_BERRIES), false));
        this.goalSelector.addGoal(5, new AvoidEntityGoal<>(this, Monster.class, 16.0F, 1.6F, 1.4F, livingEntity -> livingEntity.getType().is(EntityTypeTags.UNDEAD)));
        this.goalSelector.addGoal(6, new AvoidPlayerGoal(this, 16.0F, 1.6F, 1.4F, Entity::isSprinting));
        this.goalSelector.addGoal(7, new FollowParentGoal(this, 1.15D));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.4F));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(Items.SWEET_BERRIES);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack heldStack = player.getItemInHand(hand);
        if (heldStack.is(Items.GLASS_BOTTLE)) {
            if (!this.level().isClientSide) {
                ItemStack stinkPotion = PotionContents.createItemStack(Items.POTION, ModPotions.STINK_POTION);
                if (!player.getAbilities().instabuild) {
                    heldStack.shrink(1);
                }
                if (!player.getInventory().add(stinkPotion)) {
                    player.drop(stinkPotion, false);
                }
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        return super.mobInteract(player, hand);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return ModEntities.SKUNK.get().create(level);
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

    static class AvoidPlayerGoal extends AvoidEntityGoal<Player> {

        public AvoidPlayerGoal(PathfinderMob mob, float maxDistance, double walkSpeedModifier, double sprintSpeedModifier, Predicate<LivingEntity> predicate) {
            super(mob, Player.class, maxDistance, walkSpeedModifier, sprintSpeedModifier, predicate);
        }

        @Override
        public boolean canUse() {
            this.toAvoid = this.mob.level().getNearestPlayer(this.mob.getX(), this.mob.getY(), this.mob.getZ(), this.maxDist, entity -> entity.isSprinting() && !entity.isSpectator());
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

    static class SkunkSprayGoal extends Goal {
        private final PathfinderMob mob;
        private LivingEntity toAvoid;
        private int ticks = 0;

        public SkunkSprayGoal(PathfinderMob mob) {
            super();
            this.mob = mob;
        }

        @Override
        public boolean canUse() {
            this.toAvoid = this.mob.level().getNearestEntity(this.mob.level().getEntitiesOfClass(Monster.class, this.mob.getBoundingBox().inflate(4.0F, 3.0F, 4.0F), entity -> entity.getType().is(EntityTypeTags.UNDEAD)),
                    TargetingConditions.forCombat().range(4.0F).selector(livingEntity -> livingEntity.getType().is(EntityTypeTags.UNDEAD)), this.mob, this.mob.getX(), this.mob.getY(), this.mob.getZ());
            if (this.toAvoid == null) {
                this.toAvoid = this.mob.level().getNearestPlayer(this.mob.getX(), this.mob.getY(), this.mob.getZ(), 4.0F, Entity::isSprinting);
            }
            if (toAvoid != null) {
                return true;
            } else if (this.mob.getLastDamageSource() != null && this.mob.getLastDamageSource().getEntity() instanceof LivingEntity livingEntity) {
                this.toAvoid = livingEntity;
                return true;
            }
            return false;
        }

        @Override
        public boolean canContinueToUse() {
            return this.ticks > 0;
        }

        @Override
        public void start() {
            this.ticks = 50;
            this.mob.getNavigation().stop();
            this.mob.lookAt(EntityAnchorArgument.Anchor.FEET, new Vec3(2 * this.mob.getX() - this.toAvoid.getX(), this.mob.getY(), 2 * this.mob.getZ() - this.toAvoid.getZ()));
            ((SkunkEntity) this.mob).triggerAnim("Spray", "spray");
            if (!this.toAvoid.hasEffect(ModEffects.STINK_EFFECT)) {
                this.toAvoid.addEffect(new MobEffectInstance(ModEffects.STINK_EFFECT, 2400));
            }
        }

        @Override
        public void tick() {
            if (this.ticks > 0) {
                this.ticks--;
            }
        }

        @Override
        public boolean isInterruptable() {
            return false;
        }
    }

}
