import { readFile } from 'fs/promises';
import { dirname, join } from 'path';
import { fileURLToPath } from 'url';

const __dirname = dirname(fileURLToPath(import.meta.url));
const filePath = join(__dirname, 'data.json');

const fileContent = await readFile(filePath, 'utf-8');
const state = JSON.parse(fileContent);

export default state