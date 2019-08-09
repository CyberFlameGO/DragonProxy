/*
 * DragonProxy
 * Copyright (C) 2016-2019 Dragonet Foundation
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * You can view the LICENSE file for more details.
 *
 * @author Dragonet Foundation
 * @link https://github.com/DragonetMC/DragonProxy
 */
package org.dragonet.proxy.network.session.cache;

import com.flowpowered.math.vector.Vector2f;
import com.github.steveice10.mc.protocol.data.game.chunk.BlockStorage;
import com.github.steveice10.mc.protocol.data.game.chunk.Chunk;
import com.github.steveice10.mc.protocol.data.game.chunk.Column;
import com.github.steveice10.mc.protocol.data.game.entity.metadata.ItemStack;
import com.github.steveice10.mc.protocol.data.game.world.block.BlockState;
import com.github.steveice10.opennbt.NBTIO;
import com.nukkitx.nbt.tag.CompoundTag;
import com.nukkitx.protocol.bedrock.data.ItemData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.dragonet.proxy.data.chunk.ChunkData;
import org.dragonet.proxy.data.chunk.ChunkSection;
import org.dragonet.proxy.network.session.ProxySession;
import org.dragonet.proxy.network.translator.types.ItemTranslator;
import org.dragonet.proxy.network.translator.types.item.ItemEntry;

import java.io.IOException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Log4j2
public class ChunkCache implements Cache {
    @Getter
    private Map<Vector2f, Column> chunks = new HashMap<>(); // TODO

    private ProxySession session;

    @Override
    public void purge() {
        chunks.clear();
    }

    public ChunkData translateChunk(int columnX, int columnZ) {
        Vector2f columnPos = new Vector2f(columnX, columnZ);

        if (chunks.containsKey(columnPos)) {
            Column column = chunks.get(columnPos);
            ChunkData chunkData = new ChunkData();
            chunkData.sections = new ChunkSection[16];

            for (int i = 0; i < 16; i++) {
                chunkData.sections[i] = new ChunkSection();
            }

            // TODO: translate blocks and stuff
            return chunkData;
        }
        return null;
    }
}
