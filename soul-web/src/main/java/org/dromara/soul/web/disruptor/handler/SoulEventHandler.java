/*
 *
 *  * Licensed to the Apache Software Foundation (ASF) under one or more
 *  * contributor license agreements.  See the NOTICE file distributed with
 *  * this work for additional information regarding copyright ownership.
 *  * The ASF licenses this file to You under the Apache License, Version 2.0
 *  * (the "License"); you may not use this file except in compliance with
 *  * the License.  You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.dromara.soul.web.disruptor.handler;


import com.lmax.disruptor.EventHandler;
import org.dromara.soul.web.disruptor.event.SoulDataEvent;
import org.dromara.soul.web.influxdb.service.InfluxDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *  handler data .
 * @author xiaoyu(Myth)
 */
@Component
public class SoulEventHandler implements EventHandler<SoulDataEvent> {

    private final InfluxDbService influxDbService;

    @Autowired
    public SoulEventHandler(final InfluxDbService influxDbService) {
        this.influxDbService = influxDbService;
    }

    @Override
    public void onEvent(final SoulDataEvent soulDataEvent, final long sequence, final boolean endOfBatch) {
        influxDbService.writeData(soulDataEvent.getMonitorDO());
        soulDataEvent.clear();
    }
}
