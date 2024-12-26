const getRandomItem = (arr: []) => {
  return arr[Math.floor(Math.random() * arr.length)];
};

export { getRandomItem };
