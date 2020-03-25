/*export function updateCras(array, item) {
  const result = array.slice();
  const index = result.findIndex(i => i.period === item.period && i.id === item.id);
  if (index === -1) {
    result.splice(0, 0, item);
  } else {
    result.splice(index, 1, item);
  }
  return result;
}*/

export function update(array, item) {
  const result = array.slice();
  const index = result.findIndex(i => i.id === item.id);
  if (index === -1) {
    result.splice(0, 0, item);
  } else {
    result.splice(index, 1, item);
  }
  return result;
}

export function remove(array, id) {
  const result = array.slice();
  const index = result.findIndex(i => i.id === id);
  result.splice(index, 1);
  return result;
}

export function insertItem(array, item) {
  const result = array.slice();
  result.splice(0, 0, item);
  return result;
}
