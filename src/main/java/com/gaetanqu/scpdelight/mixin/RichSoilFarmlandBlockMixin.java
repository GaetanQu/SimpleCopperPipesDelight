package com.gaetanqu.scpdelight.mixin;

import vectorwing.farmersdelight.common.block.RichSoilFarmlandBlock;
import net.lunade.copper.blocks.block_entity.leaking_pipes.LeakingPipeManager;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RichSoilFarmlandBlock.class)
public class RichSoilFarmlandBlockMixin {

    @Inject(at = @At("RETURN"), method = "hasWater", cancellable = true)
    private static void scp$hasWater(LevelReader world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {

        boolean original = cir.getReturnValue();

        if (original) return;

        boolean pipeWater = false;

        try {
            pipeWater = LeakingPipeManager.isWaterPipeNearbyBlockGetter(world, pos, 6);
        } catch (Throwable t) {
            return;
        }

        cir.setReturnValue(pipeWater);
    }
}