
# normal image build, build amd/arm with system version
#docker build -t boboboboo/big-market-app:1.0 -f ./Dockerfile .

# amd, arm manifest build
docker buildx build --platform linux/amd64,linux/arm64 -t boboboboo/big-market-app:2.0 -f ./Dockerfile --push .