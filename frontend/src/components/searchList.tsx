type Props = {
  list: Array<string>;
};

export default function SearchList({ list }: Props) {
  return (
    <>
      {list.length > 0 && (
        <div className="w-8/12 rounded-xl bg-white absolute top-12 right-1 z-10 shadow-[0_0_2px_2px_rgba(0,0,0,0.1)] ">
          {list.map((item, index) => (
            <p className="text-lg px-5 py-3 truncate" key={index}>
              {item}
            </p>
          ))}
        </div>
      )}
    </>
  );
}
