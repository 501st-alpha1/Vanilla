/*
 * This file is part of Vanilla.
 *
 * Copyright (c) 2011-2012, VanillaDev <http://www.spout.org/>
 * Vanilla is licensed under the SpoutDev License Version 1.
 *
 * Vanilla is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the SpoutDev License Version 1.
 *
 * Vanilla is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the SpoutDev License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://www.spout.org/SpoutDevLicenseV1.txt> for the full license,
 * including the MIT license.
 */
package org.spout.vanilla.world.generator.normal.biome.special;

import net.royawesome.jlibnoise.module.modifier.ScalePoint;
import org.spout.vanilla.configuration.BiomeConfiguration;
import org.spout.vanilla.world.generator.normal.biome.IcyBiome;
import org.spout.vanilla.world.generator.normal.biome.NormalBiome;

public class FrozenOceanBiome extends IcyBiome {
	private final static ScalePoint NOISE = new ScalePoint();

	static {
		NOISE.SetSourceModule(0, NormalBiome.MASTER);
		NOISE.setxScale(BiomeConfiguration.OCEAN_X_SCALE.getDouble());
		NOISE.setyScale(BiomeConfiguration.OCEAN_Y_SCALE.getDouble());
		NOISE.setzScale(BiomeConfiguration.OCEAN_Z_SCALE.getDouble());
	}

	public FrozenOceanBiome(int biomeId) {
		super(biomeId, NOISE);

		this.minDensityTerrainHeight = BiomeConfiguration.OCEAN_MIN_DENSITY_TERRAIN_HEIGHT.getByte();
		this.maxDensityTerrainHeight = BiomeConfiguration.OCEAN_MAX_DENSITY_TERRAIN_HEIGHT.getByte();

		this.minDensityTerrainThickness = BiomeConfiguration.OCEAN_MIN_DENSITY_TERRAIN_THICKNESS.getByte();
		this.maxDensityTerrainThickness = BiomeConfiguration.OCEAN_MAX_DENSITY_TERRAIN_THICKNESS.getByte();

		this.upperHeightMapScale = BiomeConfiguration.OCEAN_UPPER_HEIGHT_MAP_SCALE.getFloat();
		this.bottomHeightMapScale = BiomeConfiguration.OCEAN_BOTTOM_HEIGHT_MAP_SCALE.getFloat();

		this.densityTerrainThicknessScale = BiomeConfiguration.OCEAN_DENSITY_TERRAIN_THICKNESS_SCALE.getFloat();
		this.densityTerrainHeightScale = BiomeConfiguration.OCEAN_DENSITY_TERRAIN_HEIGHT_SCALE.getFloat();
	}

	@Override
	public String getName() {
		return "Frozen Ocean";
	}
}
