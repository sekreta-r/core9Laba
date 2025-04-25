import { check } from 'k6';
import http from 'k6/http';
import { randomString } from 'https://jslib.k6.io/k6-utils/1.4.0/index.js';
import { Writer, SCHEMA_TYPE_STRING, SchemaRegistry } from 'k6/x/kafka';

const topic = __ENV.KAFKA_TOPIC || 'var15';
const brokers = [__ENV.KAFKA_BROKER || 'hl15.zil:9094'];  // Поменял на твой брокер

const writer = new Writer({
    brokers: brokers,
    topic: topic,
});

const schemaRegistry = new SchemaRegistry(); // для сериализации строк

export const options = {
    scenarios: {
        writers: {
            executor: 'constant-vus',
            vus: __ENV.VUS_WRITE ? parseInt(__ENV.VUS_WRITE) : 2,
            duration: '1m',
            exec: 'writeScenario',
        },
        readers: {
            executor: 'constant-vus',
            vus: __ENV.VUS_READ ? parseInt(__ENV.VUS_READ) : 5,
            duration: '1m',
            exec: 'readScenario',
        },
    },
};

function generateCourierKafkaMessage() {
    return JSON.stringify({
        entity: 'COURIER',
        operation: 'POST',
        payload: {
            fullName: `Courier-${randomString(6)}`,
            transport: "bike",
            workZone: `zone-${randomString(4)}`,
        },
    });
}

export function writeScenario() {
    const msg = generateCourierKafkaMessage();

    writer.produce({
        messages: [
            {
                key: schemaRegistry.serialize({
                    data: randomString(4), // случайный ключ
                    schemaType: SCHEMA_TYPE_STRING,
                }),
                value: schemaRegistry.serialize({
                    data: msg,
                    schemaType: SCHEMA_TYPE_STRING,
                }),
            },
        ],
    });
}

export function readScenario() {
    const res = http.get('http://hl15.zil:8082/additional/stats/couriers', {
        timeout: '360s',
        headers: { 'Content-Type': 'application/json' },
    });

    check(res, { 'got courier stats': r => r.status === 200 });
}
