/*
 * This file is part of Vanilla.
 *
 * Copyright (c) 2011 Spout LLC <http://www.spout.org/>
 * Vanilla is licensed under the Spout License Version 1.
 *
 * Vanilla is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the Spout License Version 1.
 *
 * Vanilla is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the Spout License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://spout.in/licensev1> for the full license, including
 * the MIT license.
 */
package org.spout.vanilla.protocol.handler.player.pos;

import org.spout.api.entity.Entity;
import org.spout.api.entity.EntityPrefab;
import org.spout.api.geo.World;
import org.spout.api.geo.discrete.Point;
import org.spout.api.protocol.ClientSession;
import org.spout.api.protocol.MessageHandler;

import org.spout.vanilla.component.entity.living.Human;
import org.spout.vanilla.component.entity.misc.MetadataComponent;
import org.spout.vanilla.protocol.msg.player.pos.PlayerSpawnMessage;

public class PlayerSpawnHandler extends MessageHandler<PlayerSpawnMessage> {
	@Override
	public void handleClient(ClientSession session, PlayerSpawnMessage message) {
		if (!session.hasPlayer()) {
			return;
		}

		World w = session.getEngine().getDefaultWorld();
		final Point pos = new Point(w, message.getX(), message.getY(), message.getZ());

		// Create a new Player Entity with a Human Entity Component
		Entity entity = w.createEntity(pos, Human.class);
		entity.setSavable(true);
		entity.add(Human.class).setName(message.getName());
		EntityPrefab humanPrefab = session.getEngine().getFileSystem().getResource("entity://Vanilla/entities/human/human.sep");
		entity = humanPrefab.createEntity(pos);

		// Load in Metadata
		MetadataComponent metadata = entity.get(MetadataComponent.class);
		if (metadata != null) {
			metadata.setParameters(message.getParameters());
		}

		w.spawnEntity(entity);
		System.out.println(message.toString());
	}
}
